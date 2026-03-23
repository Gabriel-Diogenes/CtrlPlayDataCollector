package com.ctrlplay.datacollector.util;

import com.ctrlplay.datacollector.config.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class SeleniumUtils {

    public static boolean foiEncontradoElementos(SearchContext searcher, By by) {
        return foiEncontradoElementos(searcher, by, null, null, null, null);
    }

    public static boolean foiEncontradoElementos(SearchContext searcher, By by, Integer tentativas) {
        return foiEncontradoElementos(searcher, by, null, null, null, tentativas);
    }

    public static boolean foiEncontradoElementos(SearchContext searcher, By by1, By by2) {
        return foiEncontradoElementos(searcher, by1, by2, null, null, null);
    }

    public static boolean foiEncontradoElementos(SearchContext searcher, By by1, By by2, Integer tentativas) {
        return foiEncontradoElementos(searcher, by1, by2, null, null, tentativas);
    }

    public static boolean foiEncontradoElementos(SearchContext searcher, By by1, By by2, By by3) {
        return foiEncontradoElementos(searcher, by1, by2, by3, null, null);
    }

    public static boolean foiEncontradoElementos(SearchContext searcher, By by1, By by2, By by3, Integer tentativas) {
        return foiEncontradoElementos(searcher, by1, by2, by3, null, tentativas);
    }

    public static boolean foiEncontradoElementos(SearchContext searcher, By by1, By by2, By by3, By by4) {
        return foiEncontradoElementos(searcher, by1, by2, by3, by4, null);
    }

    public static boolean foiEncontradoElementos(SearchContext searcher, By by1, By by2, By by3, By by4, Integer tentativas) {
        if (searcher != null) {
            boolean faltaParametros = false;

            int qtdeParametros = 0;

            if (tentativas==null) {
                tentativas = 60;
            }
            System.out.println("## Buscando elementos: ");
            if (by4 != null) {
                if (by3 == null || by2 == null || by1 == null) {
                    faltaParametros = true;
                } else {
                    System.out.println(by1+", "+by2+", "+by3+" ou "+by4);
                    qtdeParametros = 4;
                }
            } else if (by3 != null) {
                if (by2 == null || by1 == null) {
                    faltaParametros = true;
                } else {

                    System.out.println(by1+", "+by2+" ou "+by3);
                    qtdeParametros = 3;
                }
            } else if (by2 != null) {
                if(by1 == null){
                    faltaParametros = true;
                } else {
                    System.out.println(by1+" ou "+by2);
                    qtdeParametros = 2;
                }
            } else if (by1 != null) {
                qtdeParametros = 1;
                System.out.println(String.valueOf(by1));
            }
            if (faltaParametros) {
                throw new IllegalArgumentException("Parametro(s) anteriores não informados.");
            }
            if (qtdeParametros > 0) {
                boolean encontrado = false;
                int countLoop = 0;
                do {
                    switch (qtdeParametros) {
                        case 1:
                            encontrado = existeElementos(searcher, by1);
                            break;
                        case 2:
                            encontrado = existeElementos(searcher, by1) || existeElementos(searcher, by2);
                            break;
                        case 3:
                            encontrado = existeElementos(searcher, by1) || existeElementos(searcher, by2) || existeElementos(searcher, by3);
                            break;
                        case 4:
                            encontrado = existeElementos(searcher, by1) || existeElementos(searcher, by2) || existeElementos(searcher, by3) || existeElementos(searcher, by4);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        System.out.println("## (!)java.lang.InterruptedException(!) Exceção ignorada ##");
                    }
                    countLoop++;
                } while (!encontrado && countLoop <= tentativas);

                System.out.println("## elemento(s)" + (encontrado ? "" : " não") + " encontrado(s) ##");

                return encontrado;
            }
        } else {
            throw new IllegalArgumentException("WebDriver não encontrado");
        }

        return false;
    }

    public static boolean existeElementos(SearchContext searcher, By by) {
        if (searcher != null && by != null) {
            List<WebElement> elementos = searcher.findElements(by);
            if (elementos.size() > 0) {
                try {
                    Thread.sleep(50);
                    for (WebElement elemento : elementos) {
                        if (elemento.isDisplayed()) {
                            return true;
                        }
                    }
                } catch (InterruptedException ie) {
                    System.out.println("## (!)java.lang.InterruptedException(!) Exceção ignorada ##");
                } catch (NoSuchElementException se) {
                    System.out.println("## (!)java.util.NoSuchElementException(!) Exceção ignorada ##");
                } catch (StaleElementReferenceException st) {
                    System.out.println("## (!)org.openqa.selenium.StaleElementReferenceException(!) Exceção ignorada ##");
                }
            }
        }
        return false;
    }
    public static boolean  verificaSeEstaNaPagina(String elemento,WebDriver driver){

        if(SeleniumUtils.foiEncontradoElementos(driver, By.cssSelector(elemento),10)){
            return true;
        }else return false;
    }

    public static String trocarParaNovaGuia(WebDriver driver) {
        String abaOriginal = driver.getWindowHandle();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.getWindowHandles().size() > 1);

        for (String aba : driver.getWindowHandles()) {
            if (!aba.equals(abaOriginal)) {
                driver.switchTo().window(aba);
                break;
            }
        }

        return abaOriginal;
    }

    public static void fecharGuiaEVoltar(WebDriver driver, String abaOriginal) {
        driver.close();
        driver.switchTo().window(abaOriginal);
    }

}
