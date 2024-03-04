import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        int numberOne = Integer.parseInt(JOptionPane.showInputDialog("Digite seu numero"));
        int numberTwo = Integer.parseInt(JOptionPane.showInputDialog("Digite seu numero"));

        int soma = numberOne + numberTwo;

        JOptionPane.showMessageDialog(null, soma);
    }
}