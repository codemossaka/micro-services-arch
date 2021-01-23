package com.mcommandes.web.controller;


import com.mcommandes.model.Commandes;
import com.mcommandes.repository.CommandesRepository;
import com.mcommandes.web.exceptions.CommandeNotFoundException;
import com.mcommandes.web.exceptions.ImpossibleAjouterCommandeException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class CommandeController {

    private final CommandesRepository commandesRepository;

    @PostMapping(value = "/commandes")
    public ResponseEntity<Commandes> ajouterCommande(@RequestBody Commandes commandes) {

        Commandes nouvelleCommandes = commandesRepository.save(commandes);

        if (nouvelleCommandes == null)
            throw new ImpossibleAjouterCommandeException("Impossible d'ajouter cette commande");

        return new ResponseEntity<Commandes>(commandes, HttpStatus.CREATED);
    }

    @GetMapping(value = "/commandes/{id}")
    public Optional<Commandes> recupererUneCommande(@PathVariable int id) {

        Optional<Commandes> commande = commandesRepository.findById(id);

        if (commande.isEmpty()) throw new CommandeNotFoundException("Cette commande n'existe pas");

        return commande;
    }
}
