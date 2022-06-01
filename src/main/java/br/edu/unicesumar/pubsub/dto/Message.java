package br.edu.unicesumar.pubsub.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    
    public static final String myUser = "igor-gorini";
    private String message;
    private String user = Message.myUser;
    private String grupo;

}
