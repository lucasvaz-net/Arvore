package Arvore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/*iuyuyuy*/
public class Main {
    private static ArvoreBinaria arvore;
    private static JTextArea textArea;
    private static JTextField inputField;

    public static void main(String[] args) {
        arvore = new ArvoreBinaria();

        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Árvore Binária");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 300));

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel treePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setFont(new Font("Arial", Font.BOLD, 14));
                g2d.setColor(Color.BLACK);
                drawTree(arvore.getRaiz(), getWidth() / 2, 30, 100, g2d);
            }

            private void drawTree(Noh noh, int x, int y, int offset, Graphics2D g2d) {
                if (noh == null)
                    return;

                g2d.drawString(String.valueOf(noh.getValor()), x - 6, y);

                if (noh.getEsquerda() != null) {
                    int xEsq = x - offset;
                    int yEsq = y + 30;
                    g2d.drawLine(x - 2, y + 5, xEsq + 2, yEsq - 5);
                    drawTree(noh.getEsquerda(), xEsq, yEsq, offset / 2, g2d);
                }

                if (noh.getDireita() != null) {
                    int xDir = x + offset;
                    int yDir = y + 30;
                    g2d.drawLine(x + 2, y + 5, xDir - 2, yDir - 5);
                    drawTree(noh.getDireita(), xDir, yDir, offset / 2, g2d);
                }
            }
        };
        treePanel.setPreferredSize(new Dimension(400, 200));
        mainPanel.add(treePanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(e -> {
            String input = inputField.getText();
            if (!input.isEmpty()) {
                int valor = Integer.parseInt(input);
                arvore.inserir(valor);
                textArea.setText(arvore.toString());
                inputField.setText("");
                treePanel.repaint();
            }
        });
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opções");
        JMenuItem inserirItem = new JMenuItem("Inserir");
        inserirItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInputDialog("Inserir Valor", "Insira o valor a ser inserido na árvore:");
            }
        });
        JMenuItem removerItem = new JMenuItem("Remover");
        removerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInputDialog("Remover Valor", "Insira o valor a ser removido da árvore:");
            }
        });
        JMenuItem preOrdemItem = new JMenuItem("Pré-ordem");
        preOrdemItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printTravessia("Pré-ordem", arvore.preOrdem());
            }
        });
        JMenuItem emOrdemItem = new JMenuItem("Em ordem");
        emOrdemItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printTravessia("Em ordem", arvore.emOrdem());
            }
        });
        JMenuItem posOrdemItem = new JMenuItem("Pós-ordem");
        posOrdemItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printTravessia("Pós-ordem", arvore.posOrdem());
            }
        });
        menu.add(inserirItem);
        menu.add(removerItem);
        menu.addSeparator();
        menu.add(preOrdemItem);
        menu.add(emOrdemItem);
        menu.add(posOrdemItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private static void showInputDialog(String title, String message) {
        String input = JOptionPane.showInputDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
        if (input != null && !input.isEmpty()) {
            try {
                int valor = Integer.parseInt(input);
                if (title.equals("Inserir Valor")) {
                    arvore.inserir(valor);
                } else if (title.equals("Remover Valor")) {
                    arvore.remover(valor);
                }
                textArea.setText(arvore.toString());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor inválido! Insira um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void printTravessia(String title, List<Integer> travessia) {
        StringBuilder sb = new StringBuilder();
        for (Integer valor : travessia) {
            sb.append(valor).append(" ");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), title, JOptionPane.INFORMATION_MESSAGE);
    }
}
