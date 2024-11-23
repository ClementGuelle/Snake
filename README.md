Pour lancer le programme : 

Aller à la racine du projet puis lancer la commande ( Sous linux )

```cmd
javac @compile.list -d class
```

Ou ( Sous windows )

```cmd
javac $(Get-Content compile.list) -d class
```

Ensuite aller dans le .class 
``` cmd
cd class
```

Puis lancer le Controleur
```cmd
java src.Controleur
```

Amusez vous.
