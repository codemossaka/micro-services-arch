package com.clientui.controller;

import com.clientui.beans.CommandeBean;
import com.clientui.beans.ProductBean;
import com.clientui.proxies.MicroserviceCommandesProxy;
import com.clientui.proxies.MicroserviceProduitsProxy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class ClientController {
    private final MicroserviceProduitsProxy produitsProxy;

    private final MicroserviceCommandesProxy commandesProxy;

    @RequestMapping("/")
    public String accueil(Model model) {
        List<ProductBean> productBeans = produitsProxy.listeDesProduits();
        model.addAttribute("produits", productBeans);
        return "Accueil";
    }

    @RequestMapping("/details-produit/{id}")
    public String ficheProduit(@PathVariable int id, Model model) {

        ProductBean produit = produitsProxy.recupererUnProduit(id);

        model.addAttribute("produit", produit);

        return "FicheProduit";
    }

    @RequestMapping("/details-produit/commander-produit/{id}")
    public String commandeProduit(@PathVariable int id, Model model) {
        CommandeBean commandeBean = new CommandeBean();
        commandeBean.setDateCommande(new Date());
        commandeBean.setId(1);
        commandeBean.setProductId(id);
        commandeBean.setQuantite(2);
        commandeBean.setCommandePayee(true);

        CommandeBean commandeBeanResponseEntity = commandesProxy.ajouterCommande(commandeBean);
        System.out.println(commandeBeanResponseEntity);

        return "redirect:/";
    }
}
