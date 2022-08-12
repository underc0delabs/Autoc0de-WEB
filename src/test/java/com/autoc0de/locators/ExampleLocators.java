package com.autoc0de.locators;

import com.core.utility.PropertyFileReader;

public class ExampleLocators {

    public static final String BASE_URL = PropertyFileReader.getProperty("url");
    public static final String HOME_TITLE_XPATH = "xpath://*[@id=\"top\"]/section[1]/div/div/div/div/h2";
    public static final String FORO_BUTTON_XPATH = "xpath://*[@id=\"materialize-menu\"]/ul/li[2]/a";
    public static final String INGRESAR_BUTTONS_XPATH = "xpath://*[@id=\"button_login\"]/a";
    public static final String INGRESAR_LOGIN_BUTTONS_XPATH = "xpath://*[@id=\"frmLogin\"]/div/div[2]/p[1]/input";
    public static final String USUARIO_INPUT_XPATH = "xpath://*[@id=\"frmLogin\"]/div/div[2]/dl[1]/dd[1]/input";
    public static final String PASS_INPUT_XPATH = "xpath://*[@id=\"frmLogin\"]/div/div[2]/dl[1]/dd[2]/input";
    public static final String USER_LOGIN_LBL_XPATH = "xpath://*[@id=\"navbarDropdown\"]/span";
}
