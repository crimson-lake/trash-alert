package pl.zielinska.outdoor.service;

public interface RecaptchaService {
    String verifyRecaptcha(String ip, String recaptchaResponse);
}
