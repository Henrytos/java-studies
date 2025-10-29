package school.sptech;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

  public static void main(String[] args) {
    FlatLightLaf.setup();

    JFrame frame = new JFrame("Jogo da velha - SPTech");

    GameCanvas gameCanvas = new GameCanvas(new Partida());
    gameCanvas.setSize(new Dimension(450, 450));

    JLabel lblMensagem = new JLabel(gameCanvas.getPartida().getMensagem(), JLabel.CENTER);
    lblMensagem.setFont(new Font("Calibri", Font.BOLD, 30));
    lblMensagem.setBackground(Color.decode(Configuracao.FOREGROUND_COLOR));
    lblMensagem.setForeground(Color.decode(Configuracao.BACKGROUND_COLOR));
    lblMensagem.setOpaque(true);
    Border border = lblMensagem.getBorder();
    Border margin = new EmptyBorder(10,10,10,10);
    lblMensagem.setBorder(new CompoundBorder(border, margin));

    JButton btnRecomecar = new JButton("Recomeçar");
    btnRecomecar.setMargin(new Insets(10, 20, 10, 20));
    btnRecomecar.addActionListener(e -> {
      Partida novaPartida = new Partida();
      gameCanvas.setPartida(novaPartida);
      gameCanvas.render();
      lblMensagem.setText(novaPartida.getMensagem());
    });

    gameCanvas.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (gameCanvas.insertPlayerMovement(e)) {
          gameCanvas.getPartida().alternarJogador();
          gameCanvas.getPartida().validarJogada();
          lblMensagem.setText(gameCanvas.getPartida().getMensagem());
        }
      }
    });

    frame.add(gameCanvas, BorderLayout.CENTER);
    frame.add(lblMensagem, BorderLayout.NORTH);
    frame.add(btnRecomecar, BorderLayout.SOUTH);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}