# Copyright 2025 Leonardo Da Vinci Feliciano Sebasitão

# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at

#     http://www.apache.org/licenses/LICENSE-2.0

# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

"""
Script para atualizar pacotes de um projeto Java/Maven:
- Detecta o pacote antigo pela estrutura de diretórios em src/main/java
- Extrai o novo groupId do pom.xml (se não for passado via CLI)
- Converte hífens em underscores no groupId para uso em package declarations
- Renomeia diretórios de pacotes Java e remove diretórios antigos vazios
- Atualiza declarações `package` nos arquivos .java

Uso:
    python3 atualiza_pacotes.py [-h] [--project-root PROJECT_ROOT]
                                 [--old-group OLD_GROUP]
                                 [--new-group NEW_GROUP]
"""
import os
import shutil
import sys
import argparse
import xml.etree.ElementTree as ET

NAMESPACE = {'mvn': 'http://maven.apache.org/POM/4.0.0'}


def parse_arguments():
    parser = argparse.ArgumentParser(
        description='Atualiza pacotes Java renomeando diretórios e declarações de package.'
    )
    parser.add_argument(
        '--project-root', '-p',
        default=os.getcwd(),
        help='Raiz do projeto com pom.xml e src/'
    )
    parser.add_argument(
        '--old-group', '-o',
        help='GroupId antigo (e.g. com.example). Se omitido, será detectado da estrutura de pastas sob src/main/java.'
    )
    parser.add_argument(
        '--new-group', '-n',
        help='Novo groupId (e.g. org.novo). Se omitido, será extraído do pom.xml.'
    )
    return parser.parse_args()


def extract_group_from_pom(pom_path):
    tree = ET.parse(pom_path)
    root = tree.getroot()

    gid = root.find('mvn:groupId', NAMESPACE)
    if gid is None:
        parent = root.find('mvn:parent', NAMESPACE)
        gid = parent.find('mvn:groupId', NAMESPACE) if parent is not None else None

    if gid is None or not gid.text:
        raise RuntimeError('Não foi possível obter groupId do pom.xml')
    return gid.text.strip()


def detect_old_group(src_base):
    # Desce pela única cadeia de diretórios até o fim
    parts = []
    path = src_base
    while True:
        subs = [d for d in os.listdir(path)
                if os.path.isdir(os.path.join(path, d))]
        if len(subs) == 1:
            parts.append(subs[0])
            path = os.path.join(path, subs[0])
        else:
            break
    if not parts:
        raise RuntimeError('Não foi possível detectar o pacote antigo em ' + src_base)
    return '.'.join(parts)


def sanitize_group(group):
    # Substitui hífens por underscores para pacotes Java válidos
    return group.replace('-', '_')


def rename_package_directory(root_dir, old_group, new_group_sanitized):
    src_base = os.path.join(root_dir, 'src', 'main', 'java')
    old_path = os.path.join(src_base, *old_group.split('.'))
    new_path = os.path.join(src_base, *new_group_sanitized.split('.'))

    print(f"Renomeando: {old_path} → {new_path}")
    if not os.path.isdir(old_path):
        raise FileNotFoundError(f"Diretório não encontrado: {old_path}")

    os.makedirs(os.path.dirname(new_path), exist_ok=True)
    shutil.move(old_path, new_path)

    # Remove diretórios antigos vazios até src_base
    parent = os.path.dirname(old_path)
    while parent.startswith(src_base) and parent != src_base:
        if os.path.isdir(parent) and not os.listdir(parent):
            os.rmdir(parent)
            print(f"Removido diretório vazio: {parent}")
            parent = os.path.dirname(parent)
        else:
            break

    return new_path


def update_java_packages(package_dir, old_group_sanitized, new_group_sanitized):
    for dirpath, _, filenames in os.walk(package_dir):
        for fname in filenames:
            if not fname.endswith('.java'):
                continue
            path = os.path.join(dirpath, fname)
            with open(path, 'r', encoding='utf-8') as f:
                content = f.read()

            updated = content.replace(
                f'package {old_group_sanitized}',
                f'package {new_group_sanitized}'
            )
            if updated != content:
                with open(path, 'w', encoding='utf-8') as f:
                    f.write(updated)
                print(f"✔ Atualizado: {path}")


def main():
    args = parse_arguments()
    project_root = args.project_root
    pom_file = os.path.join(project_root, 'pom.xml')

    if not os.path.isfile(pom_file):
        print(f"pom.xml não encontrado em: {project_root}")
        sys.exit(1)

    src_base = os.path.join(project_root, 'src', 'main', 'java')
    if not os.path.isdir(src_base):
        print(f"Diretório src/main/java não encontrado em: {project_root}")
        sys.exit(1)

    old_group = args.old_group or detect_old_group(src_base)
    new_group = args.new_group or extract_group_from_pom(pom_file)

    old_group_sanitized = sanitize_group(old_group)
    new_group_sanitized = sanitize_group(new_group)

    try:
        package_dir = rename_package_directory(
            project_root,
            old_group,
            new_group_sanitized
        )
        update_java_packages(
            package_dir,
            old_group_sanitized,
            new_group_sanitized
        )
        print('Pacotes atualizados com sucesso!')
    except Exception as e:
        print(f'Erro: {e}')
        sys.exit(1)


if __name__ == '__main__':
    main()
