## Running Locally

(need maven and java installed)

copy war aon server

# dev franchise

db prefix : frc_

### Entity

- TypeFranchise
	+id : 
	+designation :String
	+code : String

- Requerant
	+id
	+nom : String
	+information / String text
- Cuo
	+id
	+nom : String
	+sigle : String
- EtatDemande: ENUM [EN_ATTENTE, ACCEPTE ,REFUSE, QY]
- User: à copier
	+id
	+matricule: String
	+
- role: à copier
- Dossier
	+ id
	+ nom
	+ path
	+ description
	+ presence

- Demande
	+numero: auto AlphaNum - unique - numéros d'identification de la demande
	+typefranchise: Typefranchise - FK
	+marchandise: String - obligatoire - nom de la marchandise
	+dateDepot : Date - date de dépot de la demande 
	+motif : String text - motif de la demande
	+requerant : Requérant - FK - requérant du dossier
	+bureau : Bureau - FK - bureau de la demande
	+etat: EtatDemande - etat de la demande en cours
	+attribution : User - à laquelle est attribuée le dossier dans le circuit de traitement,
	+dossiers : List<Dossier> - lien vers les dossiers rattachés
	+archive : bool - archive ou non

- Attribution
	+id
	+observation : String - observation selon l'attribution
	+demande : Demande
	+user : User
	+date : Date
