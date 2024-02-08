package com.prueba.automatizada.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/CompraProducto.feature",
        glue = "com.prueba.automatizada.stepdefinitions", //Screenplay
        snippets = CucumberOptions.SnippetType.CAMELCASE, //navegarPaginaWeb
        plugin = "pretty",
        tags = "@CompraProductos"
)
public class compraRunner {
}
