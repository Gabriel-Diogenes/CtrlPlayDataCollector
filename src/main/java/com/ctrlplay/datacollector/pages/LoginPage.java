package com.ctrlplay.datacollector.pages;

import com.ctrlplay.datacollector.exceptions.ElementoNaoEncontradoException;
import com.ctrlplay.datacollector.util.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.github.cdimascio.dotenv.Dotenv;

public class LoginPage {

    public void acessarPagina(WebDriver driver){
        driver.navigate().to("https://loja.ctrlplay.com.br/login");
    }
    public void colocarUsuário(WebDriver driver) throws InterruptedException {
        Dotenv dotenv = Dotenv.load();
        if(SeleniumUtils.foiEncontradoElementos(driver, By.cssSelector("body > div > div.login-box-body > form > div:nth-child(3) > input"))){
            String usuario = dotenv.get("LOGIN");
            driver.findElement(By.cssSelector("body > div > div.login-box-body > form > div:nth-child(3) > input")).sendKeys(usuario);
            Thread.sleep(500);
        }else {
            throw new ElementoNaoEncontradoException("Não conseguiu escrever usuario");
        }
    }

    public void colocarSenha(WebDriver driver) throws InterruptedException {
        Dotenv dotenv = Dotenv.load();
        if(SeleniumUtils.foiEncontradoElementos(driver,By.cssSelector("body > div > div.login-box-body > form > div:nth-child(5) > input"))){
            String senha = dotenv.get("SENHA");
            driver.findElement(By.cssSelector("body > div > div.login-box-body > form > div:nth-child(5) > input")).sendKeys(senha);
            Thread.sleep(500);
        }else {
            throw new ElementoNaoEncontradoException("Não conseguiu escrever senha");
        }
    }

    public void logar(WebDriver driver) throws InterruptedException {
        if (SeleniumUtils.foiEncontradoElementos(driver,By.cssSelector("body > div > div.login-box-body > form > div.row > div.col-xs-5 > button"))){
            driver.findElement(By.cssSelector("body > div > div.login-box-body > form > div.row > div.col-xs-5 > button")).click();
            Thread.sleep(500);
        }else {
            throw new ElementoNaoEncontradoException("Não conseguiu escrever clicar no botão de entrar");
        }
    }

    public void clicarNaAbaCliente(WebDriver driver) throws InterruptedException {
        if (SeleniumUtils.verificaSeEstaNaPagina("body > div.wrapper > header > nav > div > ul > li > a > span",driver)){
            if (SeleniumUtils.foiEncontradoElementos(driver,By.cssSelector("#sidebarMenu > li:nth-child(20) > a"))){
                driver.findElement(By.cssSelector("#sidebarMenu > li:nth-child(20) > a")).click();
                Thread.sleep(1000);
            }
        }else{
            throw new ElementoNaoEncontradoException("Elemento não encontrado ou não conseguiu realizar o login");
        }
    }
}
