#!/bin/bash
# remove_git_dirs.sh
# Remove todas as pastas .git do diretório atual e seus subdiretórios

echo "Procurando e removendo pastas .git..."

# Encontra e remove todas as pastas .git (sem erro caso não existam)
find . -type d -name ".github" -exec rm -rf {} + 2>/dev/null

echo "Remoção concluída. Todas as pastas .github foram deletadas."
