package school.sptech;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas {

  private static final int COLUMNS = 3;
  private static final int ROWS = 3;
  private static final int PADDING = 30;

  private Partida partida;

  public GameCanvas(Partida partida) {
    this.partida = partida;
  }

  @Override
  public void addNotify() {
    super.addNotify();
    createBufferStrategy(2);
  }

  @Override
  public void paint(Graphics g) {
    render();
  }

  public void render() {
    BufferStrategy bs = getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(2);
      return;
    }

    Graphics g = bs.getDrawGraphics();
    try {
      drawGame(g);
    } finally {
      g.dispose();
    }

    bs.show();
    Toolkit.getDefaultToolkit().sync();
  }

  public void drawGame(Graphics g) {
    int width = this.getWidth();
    int height = this.getHeight();
    int gridWidth = width / COLUMNS;
    int gridHeight = height / ROWS;

    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g.setColor(Color.decode(Configuracao.BACKGROUND_COLOR));
    g.fillRect(0, 0, width, height);

    g2d.setStroke(new BasicStroke(8));
    g.setColor(Color.decode(Configuracao.FOREGROUND_COLOR));

    for (int y = 1; y < ROWS; y++) {
      g.drawLine(0, y * gridHeight, width, y * gridHeight);
    }

    for (int x = 1; x < COLUMNS; x++) {
      g.drawLine(x * gridWidth, 0, x * gridWidth, height);
    }

    g2d.setStroke(new BasicStroke(8));

    for (int my = 0; my < partida.getMatriz().length; my++) {
      for (int mx = 0; mx < partida.getMatriz()[my].length; mx++) {
        Integer valor = partida.getMatriz()[my][mx];
        if (valor != null) {
          int gridX = mx * gridWidth;
          int gridY = my * gridHeight;

          if (valor == 1) {
            g.setColor(Color.decode(Configuracao.PLAYER_1_COLOR));
            g.drawLine(gridX + PADDING, gridY + PADDING, gridX + gridWidth - PADDING, gridY + gridHeight - PADDING);
            g.drawLine(gridX + PADDING, gridY + gridHeight - PADDING, gridX + gridWidth - PADDING, gridY + PADDING);
          } else if (valor == 2) {
            g.setColor(Color.decode(Configuracao.PLAYER_2_COLOR));
            g.drawArc(gridX + PADDING, gridY + PADDING, gridWidth - PADDING * 2, gridHeight - PADDING * 2, 0, 360);
          }
        }
      }
    }
  }

  public boolean insertPlayerMovement(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();

    int gridWidth = this.getWidth() / COLUMNS;
    int gridHeight = this.getHeight() / ROWS;

    int gridX = x / gridWidth;
    int gridY = y / gridHeight;

    boolean result = this.partida.inserirValor(gridX, gridY);
    this.render();
    return result;
  }

  public Partida getPartida() {
    return this.partida;
  }

  public void setPartida(Partida partida) {
    this.partida = partida;
  }
}
