package controllers;

import models.FormBundleInfo;
import views.RegistroForm;

public class RegistroFormController {

    RegistroForm view;
    FormBundleInfo bundle;

    public RegistroFormController(RegistroForm view, FormBundleInfo bundle) {
        this.view = view;
        this.bundle = bundle;
    }

}
