# Elaborato_SW_ENG Link della consegna: https://elearning.unibs.it/pluginfile.php/824904/mod_resource/content/2/Elaborato_SE_2023_24.pdf

# Generalità: 
L’applicazione è tesa a supportare lo scambio di ore di prestazioni d’opera relative a diverse attività afferenti ad alcune categorie, dette categorie foglia, stabilite dal configuratore.
Il configuratore definisce anche delle categorie non foglia, ciascuna delle quali è dotata di un campo caratteristico che può assumere un valore appartenente a un dominio finito (non vuoto) di valori discreti. Il nome del campo dipende dalla categoria di appartenenza, così come il dominio di suddetto campo. A ciascun valore che cade nel dominio di un campo può essere associata una descrizione. Una categoria si articola in una o più (sotto)categorie, una per ciascun valore assumibile dal suo campo caratteristico. Ciascuna (sotto)categoria è a sua volta suddividibile ricorsivamente, e così via, secondo una gerarchia ad albero. Le categorie foglia dell’albero non sono dotate di alcun campo.


# Versione 1: 
La prima versione dell’applicazione consente l’accesso del solo configuratore. Ogni configuratore effettua il primo accesso sfruttando credenziali predefinite (comunicate a ciascun nuovo configuratore autorizzato a registrarsi), che lo qualificano come appartenente al gruppo (non vuoto, eventualmente individuale) dei configuratori dell’applicazione. Nell’ambito della sessione aperta al primo accesso, egli dovrà immediatamente scegliere credenziali personali, da usare in tutti gli accessi successivi: solo a valle di tale scelta egli potrà operare sul back-end dell’applicazione. Lo username di ciascun configuratore lo individua univocamente.
La prima versione dell’applicazione consente al configuratore di
• introdurre un nuovo comprensorio geografico,
• introdurre una nuova gerarchia di categorie,
• dotare ogni categoria non foglia di tale gerarchia di nome, campo caratteristico e dominio dello stesso, nonché di una eventuale descrizione per ciascun valore appartenente a suddetto domini,
stabilire il valore dei fattori di conversione relativi a tutte le coppie distinte di categorie foglia, dove tali categorie foglia appartengono entrambe alla gerarchia appena creata oppure una appartiene alla gerarchia appena creata e l’altra appartiene a una gerarchia preesistente;
• salvare in modo persistente tutte le informazioni di cui ai punti precedenti,
• visualizzare ciascun comprensorio geografico presente,
• visualizzare ciascuna gerarchia presente e tutte le informazioni a corredo della stessa,
• visualizzare tutti i fattori di conversione che coinvolgono come prima categoria una
categoria foglia assegnata.
