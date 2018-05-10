package net.codejava.hibernate.EventVisualization.client;

public class SignInController {
	
	private ClientView clientView;
	private SignInView signInView = new SignInView();
	private ClientController client;

	
	public SignInController(SignInView signInView) {
		this.signInView = signInView;
		
		clientView = new ClientView();
		//client = new Client();
		
	}
	
	
	public void btnSignInClicked() { /*
		String[] data = signInView.getSignInData();
		String givenUsername = data[0];
		String givenPassword = data[1];
		//String type = userService.signIn(givenUsername, givenPassword);
		List<Object[]> data = 
		
		if (type != null) { // user found
			// close this window and open the user's specific window					
			if (type.equals("true")) {
				adminView.start();
				signInView.close();					
			} else if (type.equals("false")) {
				chemistView.start();
				signInView.close();
			}
		} else {
			signInView.displayError("The user doesn't exist");
		}*/
	}
}
