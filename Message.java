package p2;

import java.io.Serializable;

import javax.swing.*;

public class Message implements Serializable {
	private String text;
	private Icon icon;


	//Constructor to create a Message Object, takes a String and an Icon

	public Message(String text, Icon icon) {
		this.text = text;
		this.icon = icon;
	}


	// returns the String object used as text
	public String getText() {
		return text;
	}

	//returns the icon object 
	public Icon getIcon() {
		return icon;
	}

}
