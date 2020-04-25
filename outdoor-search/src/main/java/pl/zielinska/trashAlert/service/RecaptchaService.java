package pl.zielinska.trashAlert.service;

public interface RecaptchaService {
    String verifyRecaptcha(String ip, String recaptchaResponse);
}
