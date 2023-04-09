# ApiPokemon
## Contexe:
C'est une application qui nous permet de consommer les informations sur tous les PoKémons à partir d'un API  pour afficher une liste de Pokémons avec leurs images, leurs noms, leurs types, leurs faiblesses et leurs informations de taille et de poids.

## L'URL de l'API qui affiche la liste des PoKémons sous forme d'images: 
[API](https://pokeapi.co/api/v2/pokemon/) - Ce lien renvoie une liste paginée de tous les PoKémons

<img src="https://user-images.githubusercontent.com/81178741/230798484-6d7f94ab-8fbe-4088-9014-4abd6a158a04.jpeg" width=200>

HttpURLConnection est utilisé pour récupérer des données depuis une URL distante qui contient les informations sur les Pokemons en utilisant la méthode GET. 
On récupère le contenu JSON de la page qui est ensuite converti en objet JSONObject en utilisant InputStreamReader. 

Une fois que les données sont récupérées, elles sont stockées dans une ArrayList de Pokémons et utilisées pour créer un adaptateur personnalisé (MyAdapter) qui est ensuite utilisé pour remplir la GridView. Lorsque l'utilisateur clique sur un élément de la GridView, une activité de détail (detailActivity) est lancée pour afficher des informations détaillées sur le Pokémon sélectionné.
## L'URL de l'API qui affiche les détails du PoKémon selon son {id ou nom}: 
[API/{id ou nom}](https://pokeapi.co/api/v2/pokemon/) - ajouter l'id ou le nom aprés le /

<img src="https://user-images.githubusercontent.com/81178741/230798573-a3d5969f-037c-45d5-a347-2f5ea5089a6b.jpeg" width=200>


