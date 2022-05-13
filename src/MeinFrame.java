import javax.swing.JFrame;

public class MeinFrame extends JFrame {

	public MeinFrame(String titel) {
		super (titel);
		setBounds(200,500,670,650);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		add (new MeinPanel());
		setVisible (true);
	}
	
}
