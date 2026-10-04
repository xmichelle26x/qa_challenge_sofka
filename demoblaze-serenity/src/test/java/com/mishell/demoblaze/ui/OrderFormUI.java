package com.mishell.demoblaze.ui;

import net.serenitybdd.screenplay.targets.Target;

public class OrderFormUI {

    public static final Target NAME_FIELD      = Target.the("Campo nombre").locatedBy("#name");
    public static final Target COUNTRY_FIELD   = Target.the("Campo país").locatedBy("#country");
    public static final Target CITY_FIELD      = Target.the("Campo ciudad").locatedBy("#city");
    public static final Target CARD_FIELD      = Target.the("Campo tarjeta").locatedBy("#card");
    public static final Target MONTH_FIELD     = Target.the("Campo mes").locatedBy("#month");
    public static final Target YEAR_FIELD      = Target.the("Campo año").locatedBy("#year");

    public static final Target PURCHASE_BUTTON =
        Target.the("Botón 'Purchase'")
              .locatedBy("//button[text()='Purchase']");

    public static final Target CONFIRMATION_TITLE =
        Target.the("Título del modal de confirmación")
              .locatedBy(".sweet-alert h2");

    public static final Target CONFIRMATION_OK_BUTTON =
        Target.the("Botón OK del modal")
              .locatedBy(".sweet-alert .confirm");
}