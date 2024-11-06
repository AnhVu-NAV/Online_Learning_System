/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_mail;

/**
 *
 * @author ADMIN
 */
public interface IJavaMail {

    boolean send(String toEmail, String subject, String messageContent);
}
