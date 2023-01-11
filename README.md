# Les_aventuriers_du_rail2


Comment fonctionne notre application ? 
Tout d'abord lorsque vous allez la lancer une page s'ouvrira et il suffira d'appuyer sur le bouton Jouer pour commencer la partie.
Vous allez ensuite être emmenés sur une nouvelle page qui vous permettera de choisir un fichier XML qui comportera votre map que vous avez conçu dans la première application, vous pourrez également définir le nombre de joueurs que vous voulez.
Une fois cela fait, la map s'affichera sur la page avec vos cartes, la pioche retourner, la pioche ouverte, la pioche pour les carte objectif et le bouton qui vous permettera de remplir une sections avec vos wagons

Pour jouer en mode solo il suffit de mettre 1 seul joueur. Ensuite la map s'ouvrira et vous pourrez choisir autant de carte que vous souhaitez en cliquant soit sur les cartes wagons qui sont retournés ou soit sur la carte retournée en haut gauche de la page, vous pourrez également choisir les cartes objectifs en cliquant sur la pioche retourné mais qui se situe en dessous des cartes wagons constituant la pioche.
Pour utiliser les wagons qui sont dans votre main, il suffit de cliquer sur le bouton remplir section et de mettre dans les listes déroulantes les villes que vous voulez reliés.
Une fois que votre nombre de pion est en dessous du nombre de wagon fin que vous avez initialisé dans le concepteur la dernière manche est enclenché et il vous reste plus qu'a faire une action(prendre une carte, remplir une section, prendre une carte objectif) pour que la fin de la partie se déclanche.
À la fin de la partie une nouvelle page s'ouvre et vous pouvez accéder à votre score.a

Pour jouer à plusieurs il suffit de mettre le nombre de joueur que vous souhaitez sur la page où vous importez votre XML, ensuite une partie se lancera avec votre map.
le mode multijoueur est différent du mode solo car vous avez des tours par personnes.
Un tour est enclanché quand le joueur fait une action tel que :
- prendre deux cartes wagons dans la pioche
- prendre une carte locomotive dans la pioche
- prendre une carte objectif
- remplir une section reliant deux noeuds

Lorsque vous prenez une section des petits ronds s'affiche sur les elles de la couleur du joueur, et oui chaque joueur possède une couleur différente, bien évidemment vous verrez, lorsque se sera votre tour la couleur de vos adversaires sur les sections, et vous ne pourrez pas à votre remplir une section de votre adversaire.

La dernière manche est déclanchée lorsqu'un des joueurs à dépasser le nombre de wagon fin que vous avez initialisé dans le concepteur de la map.
Chacun des joueurs pourra faire une dernière action avant que la partie se termine.
À la fin de la partie les scores de chaque joueurs sont affichés, et le gagnant de la partie est également afficher.


## Comment lancer notre application ?

•   Se placer dans le répertoire "Les_aventuriers_du_rail2"
•   javac -d ./class "@compile.list" -encoding utf8
•   java -cp ./class src.Controleur