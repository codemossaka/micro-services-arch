package com.mpaiement.web.controller;

import com.mpaiement.repository.PaiementRepository;
import com.mpaiement.model.Paiement;
import com.mpaiement.web.exceptions.PaiementExistantException;
import com.mpaiement.web.exceptions.PaiementImpossibleException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PaiementController {

    private final PaiementRepository paiementRepository;

    @PostMapping(value = "/paiement")
    public ResponseEntity<Paiement>  payerUneCommande(@RequestBody Paiement paiement){


        //Vérifions s'il y a déjà un paiement enregistré pour cette commande
        Paiement paiementExistant = paiementRepository.findByidCommande(paiement.getIdCommande());
        if(paiementExistant != null) throw new PaiementExistantException("Cette commande est déjà payée");

        //Enregistrer le paiement
        Paiement nouveauPaiement = paiementRepository.save(paiement);


        if(nouveauPaiement == null) throw new PaiementImpossibleException("Erreur, impossible d'établir le paiement, réessayez plus tard");



        //TODO Nous allons appeler le Microservice Commandes ici pour lui signifier que le paiement est accepté

        return new ResponseEntity<Paiement>(nouveauPaiement, HttpStatus.CREATED);

    }




}
