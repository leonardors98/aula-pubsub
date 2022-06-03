package br.edu.unicesumar.pubsub.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message implements Serializable{
    
    public static final String myUser = "leo-souza";
    private String message;
    private String user = Message.myUser;
    private String grupo;

}
