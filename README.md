# Explicação da estrutura do código

## Game/Assets

### Ator
  - `String imagePath`
  - `int depth`
  - `int x, y`
  - `boolean isVisible`
- **Sobre**:
  - Todo elemento do jogo é um ator, ou seja, tem um `(x, y)` e pode ser desenhado na tela.
  - Ideal para textos.

### Character (extends Ator)
  - `int row, col`
- **Sobre**:
  - Todo `Ator` com `(x, y)` restrito ao tabuleiro é chamado de personagem, ou seja, a movimentação é dada pelas `row` e `col`.
  - Ideal para o jogador;

### Nível
  - `Manager manager`
  - `List<Actor> actors`
- **Sobre**:
  - Cria e gerencia prefabs de atores.
  - Fornece funções utilitárias para interações entre atores (ex.: encontrar outra classe de personagem no mesmo nível).
  - Dispara listeners para characters (ex.: teclas pressionadas, eventos baseados em tempo).

## Game/Engine

### Manager
- **Propriedades**:
  - `Level currentLevel`
- **Sobre**:
  - Gerencia a renderização do `currentLevel` e a troca de níveis.
  - Dispara listeners para eventos (ex.: teclas pressionadas, eventos baseados em tempo).

### Surface (extends JPanel)
  - `List<Actor> actors`
- **Sobre**:
  - Desenha todos os atores de um nível específico usando as funções de `Graphics` do `JPanel`.
  - Responsável pela renderização gráfica do jogo.


## Game/Prefabs
  - Local onde programadores irão criar os objetos próprios de seu jogo.
  - Contem alguns objetos e um script de movimentação do jogador.