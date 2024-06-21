
package campominado;

/**
 *
 * @author franciscampos91
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CampoMinado extends JFrame implements ActionListener, MouseListener {

    int linha = 10;
    int coluna = 10;
    int numMinas = 10;
    GridLayout quadro = new GridLayout(linha, coluna);
    private JLabel lblbombas;
    private JLabel lblNbombas;
    private JButton btnNovo = new JButton();
    boolean[] Minas = new boolean[linha * coluna];
    boolean[] clique = new boolean[linha * coluna];
    boolean perdeu = false;
    boolean ganhou = false;
    int[] vetor = new int[linha * coluna];
    JButton[] botao = new JButton[linha * coluna];
    boolean[] clicado = new boolean[linha * coluna];
    Container ct;
    JPanel painel = new JPanel();

    public CampoMinado() {
        setSize(510, 450);
        setTitle(":: Campo Minado ::");
        CentralizarFrame(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        ct = getContentPane();
        ct.setLayout(null);
        painel.setBounds(3, 71, 490, 300);
        painel.setLayout(new GridLayout(10, 10, 2, 2));
        novocampoII();
        for (int i = 0; i < (linha * coluna); i++) {
            painel.add(botao[ i]);
        }
        JMenuBar menu = new JMenuBar();
        menu.setBounds(0, 0, 100, 20);
        JMenuItem mnuSobre = new JMenuItem();
        JMenuItem mnuSair = new JMenuItem();
        mnuSobre.setText("Sobre");
        mnuSair.setText("Sair");
        menu.add(mnuSobre);
        menu.add(mnuSair);
        lblbombas = new JLabel(String.valueOf(numMinas));
        lblbombas.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblbombas.setForeground(Color.red);
        lblbombas.setBounds(30, 38, 100, 30);
        lblNbombas = new JLabel("N° de bombas:");
        lblNbombas.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblNbombas.setBounds(3, 20, 100, 30);
        btnNovo.setBounds(230, 28, 36, 36);
        btnNovo.setFocusable(false);
        btnNovo.addActionListener(this);
        btnNovo.setIcon(new ImageIcon("o-o.jpg"));
        ct.add(menu);
        ct.add(lblbombas);
        ct.add(lblNbombas);
        ct.add(painel);
        ct.add(btnNovo);

        this.setVisible(true);
        mnuSair.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSairActionPerformed(evt);
            }
        });
        mnuSobre.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSobre(evt);
            }
        });

    }

    private void CentralizarFrame(JFrame frame) {
        Dimension paneSize = frame.getSize();
        Dimension screenSize = frame.getToolkit().getScreenSize();
        frame.setLocation((screenSize.width - paneSize.width) / 2, (screenSize.height - paneSize.height) / 2);
    }

    private void mnuSobre(java.awt.event.ActionEvent evt) {
        JLabel CampoMinado = new JLabel("Campo Minado");
        CampoMinado.setFont(new Font("Tahoma", Font.BOLD, 16));
        CampoMinado.setBounds(125, 10, 300, 20);
        JLabel Desenvolvido = new JLabel("Desenvolvido por:");
        Desenvolvido.setFont(new Font("Tahoma", Font.BOLD, 12));
        Desenvolvido.setBounds(130, 50, 300, 20);
        JLabel Francis = new JLabel("Francis Reginaldo de Góes Campos");
        Francis.setFont(new Font("Tahoma", Font.BOLD, 14));
        Francis.setBounds(60, 120, 400, 20);
        JLabel aps = new JLabel("Atividades Práticas Supervisionadas");
        aps.setFont(new Font("Tahoma", Font.BOLD, 12));
        aps.setBounds(20, 180, 400, 20);
        JLabel cc = new JLabel("Unip - Ciência da Computação");
        cc.setBounds(100, 200, 300, 20);
        JFrame sobre = new JFrame();
        sobre.setBounds(3, 3, 400, 300);
        sobre.setLayout(null);
        sobre.getContentPane().add(CampoMinado);
        sobre.getContentPane().add(Desenvolvido);
        sobre.getContentPane().add(Francis);
        sobre.getContentPane().add(aps);
        sobre.getContentPane().add(cc);
        sobre.setTitle(":: Sobre Campo Minado ::");
        CentralizarFrame(sobre);
        sobre.setVisible(true);
    }

    private void mnuSairActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    public void InserirMinas() {
        int needed = numMinas;
        while (needed > 0) {
            int x = (int) Math.floor(Math.random() * linha);
            int y = (int) Math.floor(Math.random() * coluna);
            if (!Minas[ (linha * y) + x]) {
                Minas[ (linha * y) + x] = true;
                needed--;
            }
        }
    }

    public void InserirNumeros() {
        for (int x = 0; x < linha; x++) {
            for (int y = 0; y < coluna; y++) {
                int cur = (linha * y) + x;
                if (Minas[ cur]) {
                    vetor[ cur] = 0;
                    continue;
                }
                int temp = 0;
                boolean e = (x - 1) >= 0;
                boolean d = (x + 1) < linha;
                boolean c = (y - 1) >= 0;
                boolean b = (y + 1) < coluna;
                int esquerda = (linha * (y)) + (x - 1);
                int direita = (linha * (y)) + (x + 1);
                int acima = (linha * (y - 1)) + (x);
                int acimaesquerda = (linha * (y - 1)) + (x - 1);
                int acimadireita = (linha * (y - 1)) + (x + 1);
                int abaixo = (linha * (y + 1)) + (x);
                int abaixoesquerda = (linha * (y + 1)) + (x - 1);
                int abaixodireita = (linha * (y + 1)) + (x + 1);
                if (c) {
                    if (Minas[ acima]) {
                        temp++;
                    }
                    if (e) {
                        if (Minas[ acimaesquerda]) {
                            temp++;
                        }
                    }
                    if (d) {
                        if (Minas[ acimadireita]) {
                            temp++;
                        }
                    }
                }
                if (b) {
                    if (Minas[ abaixo]) {
                        temp++;
                    }
                    if (e) {
                        if (Minas[ abaixoesquerda]) {
                            temp++;
                        }
                    }
                    if (d) {
                        if (Minas[ abaixodireita]) {
                            temp++;
                        }
                    }
                }
                if (e) {
                    if (Minas[ esquerda]) {
                        temp++;
                    }
                }
                if (d) {
                    if (Minas[ direita]) {
                        temp++;
                    }
                }
                vetor[ cur] = temp;
            }
        }
    }

    public void novocampoII() {
        for (int x = 0; x < linha; x++) {
            for (int y = 0; y < coluna; y++) {
                Minas[ (linha * y) + x] = false;
                clicado[ (linha * y) + x] = false;
                clique[ (linha * y) + x] = true;
                botao[ (linha * y) + x] = new JButton( /*"" + ( x * y )*/);
                botao[ (linha * y) + x].setPreferredSize(new Dimension(45, 45));
                botao[ (linha * y) + x].addActionListener(this);
                botao[ (linha * y) + x].addMouseListener(this);
            }
        }
        InserirMinas();
        InserirNumeros();
    }

    public void novocampo() {
        for (int x = 0; x < linha; x++) {
            for (int y = 0; y < coluna; y++) {
                Minas[ (linha * y) + x] = false;
                clicado[ (linha * y) + x] = false;
                clique[ (linha * y) + x] = true;
                botao[ (linha * y) + x].setEnabled(true);
                botao[ (linha * y) + x].setText("");
            }
        }
        InserirMinas();
        InserirNumeros();
        for (int x = 0; x < 100; x++) {
            botao[x].setIcon(null);
        }
        perdeu = false;

    }

    public static void main(String[] args) {
        new CampoMinado();
    }

    public void actionPerformed(ActionEvent e) {
        if (!ganhou) {
            for (int x = 0; x < linha; x++) {
                for (int y = 0; y < coluna; y++) {
                    if (e.getSource() == botao[ (linha * y) + x] && !ganhou && clique[ (linha * y) + x]) {
                        doCheck(x, y);
                        break;
                    }
                }
            }
        }
        if (e.getSource() == btnNovo) {
            novocampo();
            ganhou = false;
            return;
        }
        Verifica();
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 3) {
            int n = 0;
            for (int x = 0; x < linha; x++) {
                for (int y = 0; y < coluna; y++) {
                    if (e.getSource() == botao[ (linha * y) + x]) {
                        clique[ (linha * y) + x] = !clique[ (linha * y) + x];
                    }
                    if (!clicado[ (linha * y) + x]) {
                        if (!clique[ (linha * y) + x]) {
                            botao[(linha * y) + x].setIcon(new ImageIcon("band.gif"));
                            n++;
                        } else {
                            botao[ (linha * y) + x].setText("");
                            botao[(linha * y) + x].setIcon(new ImageIcon(""));
                        }

                    }
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void doCheck(int x, int y) {
        int cur = (linha * y) + x;
        boolean l = (x - 1) >= 0;
        boolean r = (x + 1) < linha;
        boolean u = (y - 1) >= 0;
        boolean d = (y + 1) < coluna;
        int esquerda = (linha * (y)) + (x - 1);
        int direita = (linha * (y)) + (x + 1);
        int acima = (linha * (y - 1)) + (x);
        int acimaesquerda = (linha * (y - 1)) + (x - 1);
        int acimadireita = (linha * (y - 1)) + (x + 1);
        int abaixo = (linha * (y + 1)) + (x);
        int abaixoesquerda = (linha * (y + 1)) + (x - 1);
        int abaixodireita = (linha * (y + 1)) + (x + 1);
        clicado[ cur] = true;
        botao[ cur].setEnabled(false);
        if (vetor[ cur] == 0 && !Minas[ cur] && !perdeu && !ganhou) {
            if (u && !ganhou) {
                if (!clicado[ acima] && !Minas[ acima]) {
                    clicado[ acima] = true;
                    botao[ acima].doClick();
                }
                if (l && !ganhou) {
                    if (!clicado[ acimaesquerda] && vetor[ acimaesquerda] != 0 && !Minas[ acimaesquerda]) {
                        clicado[ acimaesquerda] = true;
                        botao[ acimaesquerda].doClick();
                    }
                }
                if (r && !ganhou) {
                    if (!clicado[ acimadireita] && vetor[ acimadireita] != 0 && !Minas[ acimadireita]) {
                        clicado[ acimadireita] = true;
                        botao[ acimadireita].doClick();
                    }
                }
            }
            if (d && !ganhou) {
                if (!clicado[ abaixo] && !Minas[ abaixo]) {
                    clicado[ abaixo] = true;
                    botao[ abaixo].doClick();
                }
                if (l && !ganhou) {
                    if (!clicado[ abaixoesquerda] && vetor[ abaixoesquerda] != 0 && !Minas[ abaixoesquerda]) {
                        clicado[ abaixoesquerda] = true;
                        botao[ abaixoesquerda].doClick();
                    }
                }
                if (r && !ganhou) {
                    if (!clicado[ abaixodireita] && vetor[ abaixodireita] != 0 && !Minas[ abaixodireita]) {
                        clicado[ abaixodireita] = true;
                        botao[ abaixodireita].doClick();
                    }
                }
            }
            if (l && !ganhou) {
                if (!clicado[ esquerda] && !Minas[ esquerda]) {
                    clicado[ esquerda] = true;
                    botao[ esquerda].doClick();
                }
            }
            if (r && !ganhou) {
                if (!clicado[ direita] && !Minas[ direita]) {
                    clicado[ direita] = true;
                    botao[ direita].doClick();
                }
            }
        } else {
            botao[ cur].setText("" + vetor[ cur]);
            if (!Minas[ cur] && vetor[ cur] == 0) {
                botao[ cur].setText("");
            }
        }
        if (Minas[ cur] && !ganhou) {
            //botao[ cur].setText("0");
            botao[cur].setText(null);
            botao[cur].setIcon(new ImageIcon("bomba.gif"));
            Derrota();
        }
    }

    public void Verifica() {
        for (int x = 0; x < linha; x++) {
            for (int y = 0; y < coluna; y++) {
                int cur = (linha * y) + x;
                if (!clicado[ cur]) {
                    if (Minas[ cur]) {
                        continue;
                    } else {
                        return;
                    }
                }
            }
        }
        Vitoria();

    }

    public void Vitoria() {
        if (!perdeu && !ganhou) {
            ganhou = true;
            JOptionPane.showMessageDialog(null, "Você ganhou!\nInicie outro jogo", "::Você ganhou :D ::", JOptionPane.INFORMATION_MESSAGE);
            novocampo();
            ganhou = false;
            return;
        }
    }

    public void Derrota() {
        if (!perdeu && !ganhou) {
            perdeu = true;
            for (int i = 0; i < linha * coluna; i++) {
                if (!clicado[ i]) {
                    botao[ i].doClick(0);
                }
            }
            JOptionPane.showMessageDialog(null, "Você perdeu!\nInicie outro jogo", ":: Você Perdeu :( ::", JOptionPane.ERROR_MESSAGE);
            novocampo();
        }
    }
} 
