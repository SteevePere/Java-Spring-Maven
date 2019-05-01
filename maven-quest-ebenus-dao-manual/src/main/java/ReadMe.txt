

1. A quoi servent les méthodes toString, equalsCode et HashCode ?


Réponse :

- La méthode toString() sert à récupérer une représentation d'un objet sous forme de String.
Cette réprésentation pouvant être lue et comprise par un humain permet à celui-ci de connaître l'état de l'objet au moment de l'impression (particulièrement utile pour du debug).
Lorsque l'on imprime un objet (sans avoir préalablement override), le compilateur java appelle le toString() "par défaut" de java.lang.Object. Redéfinir la méthode permet donc d'obtenir l'output désiré (en fonction des cas d'usage envisagés).

- La méthode equals() permet de comparer deux objets sur la base de leur contenu (à l'inverse de l'opérateur == qui comparera les adresses mémoire des deux objets). Elle renvoie un booléen qui a pour valeur true si l'égalité entre les deux objets est avérée.
Surcharger cette méthode permet de préciser sur quels critère(s) la comparaison doit être effectuée, ceci afin de tester une égalité "fonctionelle" entre les objets.

- La méthode hashCode() permet de récupérer le hash code d'un objet (integer). Par défaut, il est basé sur l'adresse mémoire de l'objet, en hexadécimal.
Ce hash code est utilisé par les implémentations Hash telles que HashMap, HashTable, etc., ce afin d'optimiser les performances de certaines opérations de recherche linéaires (telles que contains()), pouvant être très inefficaces dans le cas de collections de grande taille. Grâce à hashCode(), les opérations de comparaison sont effectuées sur les valeurs de l'hash code de chaque objet, permettant une nette amélioration des performances.
Par définition, si deux objets sont égaux, leur hash codes doivent également être égaux. C'est pourquoi il est important de redéfinir la méthode hashCode() si la méthode equals() a été redéfinie (une redéfinition de equals() modifie en effet les conditions selon lesquelles deux objets sont considérés égaux). On parle de "contrat" entre les deux méthodes.



2. Quelle est la différence entre :

	List et Tableau ?
	List et ArrayList ?
	Map et HashMap ?
	Set et HashSet ?
	List et Set ?


Réponse :

- List et ArrayList -

List est une séquence où chaque élément peut être un objet.
ArrayList est une implémentation de List, elle n'est pas synchronisée.

- List et Tableau -

List : liste à longueur variable,
Tableaux: liste à Longueur statique choisie à la construction

- Map et HashMap -

Map est une interface implémentée par HashMap (Map m = new HashMap()). Il existe d'autres implémentations de Map (TreeMap, WeakHashMap...).

- Set et HashSet -

Set est une interface implémentée par HashSet (Set m = new HashSet()). Il existe d'autres implémentations de Set (TreeSet, LinkedHashSet...).

- List et Set -

Set contient uniquement des éléments uniques tandis que List peut contenir des éléments en
double.
Set est non ordonné tandis que la liste est ordonnée. La liste conserve l'ordre dans lequel les objets
sont ajoutés.



3. Quels sont les avantages de l’utilisation du Design Pattern DAO ?


Réponse :

Le design pattern DAO (Data Access Object) permet d'isoler la couche applicative/métier d'un projet de sa couche de persistance (couche d'accès aux données), par le biais d'une API abstraite.

Cette interface ajoute un niveau d'abstraction entre les sources de données et leur utilisation. La couche applicative reste ainsi agnostique quant au type de sources de données. La source de données (par exemple, base de données) peut ainsi être modifiée sans que la couche applicative n'en soit impactée : Dans un tel cas de figure, il suffit de développer une implémentation spécifique à la nouvelle source de données au sein de la couche de persistance.

Au-delà de la flexibilité et stabilité offertes par ce design pattern, on notera le fait que la couche applicative est affranchie du besoin de gérer la complexité des opérations CRUD (donc pas de requêtes ou d'identifiants présents dans le code de cette couche), et s'en trouve ainsi épurée et simplifiée.
