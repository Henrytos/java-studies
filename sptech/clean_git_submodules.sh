#!/bin/bash
# Script para limpar todos os .git e .github recursivamente
# e remover submódulos do cache do repositório principal

echo "🧹 Limpando repositórios Git internos..."

# Remove pastas .git e .github em todos os subdiretórios
find . -type d \( -name ".git" -o -name ".github" \) -exec rm -rf {} + 2>/dev/null

# Remove arquivo .gitmodules se existir
if [ -f ".gitmodules" ]; then
  echo "Removendo .gitmodules..."
  rm -f .gitmodules
fi

# Limpa submódulos do cache (caso existam)
echo "🔍 Limpando submódulos antigos do cache..."
git submodule deinit -f --all 2>/dev/null
git rm -f --cached $(git submodule--helper list 2>/dev/null | awk '{print $4}') 2>/dev/null

# Atualiza o índice do Git
git add . >/dev/null 2>&1

echo "✅ Limpeza concluída!"
echo "Agora você pode rodar: git commit -m 'limpeza de .git e submódulos'"

