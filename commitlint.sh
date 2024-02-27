#!/usr/bin/env bash

# Inicia el repositorio local de git
git init

# Configura nombre y correo electrónico del autor
git config user.name "joalvarez"
git config user.email "alvarez.joaquinez@gmail.com"

# Instala solo las dependencias de desarrollo
npm install --only=dev

# Inicializa Husky
npx husky init

# Crea el hook de commit-msg con Commitlint
echo "npx --no -- commitlint --edit \$1" > .husky/commit-msg

# Elimina el contenido del archivo pre-commit si existe
if [ -f ".husky/pre-commit" ]; then
    echo "" > .husky/pre-commit
fi