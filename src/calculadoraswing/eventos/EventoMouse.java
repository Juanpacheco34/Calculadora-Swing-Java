package calculadoraswing.eventos;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author jpach
 */
public class EventoMouse extends MouseAdapter {

    private JButton button;

    public EventoMouse() {
        this.button = new JButton();
    }

    // CAMBIA DE COLOR EL BOTÓN SI ES PRESIONADO
    @Override
    public void mousePressed(MouseEvent e) {
        this.button = (JButton) e.getComponent();
        button.setBackground(Color.DARK_GRAY);
    }

    // CAMBIA DE COLOR EL BOTÓN SI ES PRESIONADO Y EL MOUSE PERMANECE ENCIMA
    @Override
    public void mouseReleased(MouseEvent e) {
        this.button = (JButton) e.getComponent();
        button.setBackground(Color.CYAN);
    }

    // CAMBIA DE COLOR EL BOTÓN SI EL MOUSE ESTÁ ENCIMA
    @Override
    public void mouseEntered(MouseEvent e) {
        this.button = (JButton) e.getComponent();
        button.setBackground(Color.CYAN);
    }

    // CAMBIA DE COLOR EL BOTÓN SI EL MOUSE NO ESTÁ ENCIMA
    @Override
    public void mouseExited(MouseEvent e) {
        this.button = (JButton) e.getComponent();
        button.setBackground(Color.DARK_GRAY);
    }

}
