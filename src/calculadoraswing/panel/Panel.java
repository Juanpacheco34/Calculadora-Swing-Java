package calculadoraswing.panel;

import calculadoraswing.eventos.EventoMouse;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Panel extends JPanel {

    private final JTextArea display;
    private String valueFirst = "";
    private String valueSecond = "";
    private boolean reset = false;
    private String operador = "";

    public Panel() {
        setLayout(new BorderLayout(10, 10));

        // DISPLAY DE CALCULADORA
        display = new JTextArea();
        display.setText("0");
        display.setFont(new Font(null, Font.BOLD, 35));
        display.setBackground(Color.BLACK);
        display.setForeground(Color.red);
        display.setEnabled(false);
        display.setPreferredSize(new Dimension(getWidth(), 150));

        add(display, BorderLayout.NORTH);

        // TECLADO DE CALCULADORA
        JPanel teclado = new JPanel();
        teclado.setLayout(new GridLayout(4, 4, 10, 10));
        teclado.setBorder(new EmptyBorder(5, 5, 5, 5));

        // FILA 1
        teclado.add(insertButton("7"));
        teclado.add(insertButton("8"));
        teclado.add(insertButton("9"));
        teclado.add(insertButton("*"));
        // FILA 2
        teclado.add(insertButton("4"));
        teclado.add(insertButton("5"));
        teclado.add(insertButton("6"));
        teclado.add(insertButton("-"));
        // FILA 3
        teclado.add(insertButton("1"));
        teclado.add(insertButton("2"));
        teclado.add(insertButton("3"));
        teclado.add(insertButton("+"));
        // FILA 4
        teclado.add(insertButton("C"));
        teclado.add(insertButton("0"));
        teclado.add(insertButton("/"));
        teclado.add(insertButton("="));

        add(teclado, BorderLayout.CENTER);
    }

    public final JButton insertButton(String text) {

        JButton button = new JButton(text) {

            // SOBRESCRIBIR EL MÉTODO PAINTCOMPONENT PARA PERSONALIZAR EL DIBUJO DEL BOTÓN
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;

                // HABILITAR ANTIALIASING PARA UN DIBUJO SUAVE
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // DIBUJAR FONDO REDONDEADO
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                // DIBUJAR BORDE REDONDEADO
                g2.setColor(getForeground());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);

                // PARA REFLEJAR EL TEXTO Y OTRAS CONFIGURACIONES EN LOS BOTONES LLAMAMOS EL MÉTODO ORIGINAL
                super.paintComponent(g);
            }
        };

        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);

        // QUITAMOS EL FOCUS QUE SE PINTA EN EL TEXTO AL PRESIONAR EL BOTÓN
        button.setFocusPainted(false);
        // ELIMINAMOS EL FONDO POR DEFECTO DEL BOTÓN PARA QUE SE PUEDA VER EL BOTÓN REDONDEADO
        button.setContentAreaFilled(false);
        // ELIMINAMOS EL BORDER DONDE SE MUESTRA EL ALTO Y ANCHO QUE ESTÁ OCUPANDO EL BOTÓN
        button.setBorderPainted(false);
        // CURSOR MANITO AL COLOCAR EL CURSOR EN EL BOTÓN
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // MANEJAMOS EFECTOS PARA EL MOUSE
        button.addMouseListener(new EventoMouse());

        button.addActionListener(e -> {

            // VALIDACIONES
            if (!this.operador.isEmpty() && "*-+/".contains(button.getText())) {
                this.operador = button.getText();
                display.setText(this.valueFirst);
            }

            if (this.reset && "*-+/".contains(button.getText())) {
                this.reset = false;
            }

            if (this.reset) {
                display.setText("0");
                this.reset = false;
            }

            // SELECCIONA EL OPERADOR CORRESPONDIENTE
            switch (button.getText()) {
                case "*" -> {
                    dataForOperation(button);
                }
                case "+" -> {
                    dataForOperation(button);
                }
                case "-" -> {
                    dataForOperation(button);
                }
                case "/" -> {
                    dataForOperation(button);
                }
                case "C" -> {
                    this.display.setText("0");
                    clearAll();
                }
                case "=" -> {
                    operations(button);
                }

                default -> {
                    // VALIDACIONES
                    if (!this.valueFirst.isEmpty()) {
                        this.valueSecond += button.getText();
                    }

                    // SI NO HAY OPERADORES SIGUE AGREGANDO VALORES
                    display.setText(("0".equalsIgnoreCase(display.getText()) ? "" : display.getText()) + button.getText());
                }
            }
        });
        return button;
    }

    // GUARDA LOS PRIMEROS VALORES, ES LLAMADO CUANDO SE DETECTA OPERADOR
    public void dataForOperation(JButton button) {
        valueFirst = display.getText();
        operador = button.getText();
        display.setText(display.getText() + "\n" + button.getText() + "\n");
    }

    // REALIZA LAS OPERACIONES ES LLAMADO CUANDO SE DETECTA EL =
    public void operations(JButton button) {

        switch (operador) {

            case "*" -> {
                display.setText(String.valueOf(Integer.parseInt(valueFirst.trim()) * Integer.parseInt(valueSecond.trim())));
            }
            case "+" -> {
                display.setText(String.valueOf(Integer.parseInt(valueFirst.trim()) + Integer.parseInt(valueSecond.trim())));
            }
            case "-" -> {
                display.setText(String.valueOf(Integer.parseInt(valueFirst.trim()) - Integer.parseInt(valueSecond.trim())));
            }
            case "/" -> {
                if ("0".equals(this.valueSecond)) {
                    display.setText("Error...");
                    clearAll();
                    break;
                }
                display.setText(String.valueOf(Double.parseDouble(valueFirst.trim()) / Double.parseDouble(valueSecond.trim())));
            }

            default -> {
                System.out.println("DE DÓNDE SALIÓ ESE OPERADOR???");
            }
        }

        this.reset = true;
        clearAll();
    }

    // LIMPIA LOS VALORES
    public void clearAll() {
        this.valueFirst = "";
        this.valueSecond = "";
        this.operador = "";
    }

}
