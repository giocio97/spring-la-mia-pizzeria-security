
pizzasList();

function pizzasList() {
    axios.get(`http://localhost:8080/api`)
        .then((result) => {

            const pizzasList = result.data;
            console.log('Richiesta ok', pizzasList);

            document.querySelector('#pizze_table').innerHTML = '';

            pizzasList.forEach(pizza => {

                
        

                document.querySelector('#pizze_table').innerHTML += `
                <tr>
                    <td scope="row" ><a href="./detail.html?id=${pizza.id}"> ${pizza.id} </a></td>
                    <td>${pizza.nome}</td>
                    <td>${pizza.descrizione}</td>
                    <td> <img src="${pizza.foto}" width="100"> </td>
                    <td>${pizza.prezzo}€</td>
                    <td style="vertical-align: middle;"><a class="btn btn-danger" onclick="deletePizza(${pizza.id})"><i class="fa-solid fa-trash"></i></a></td>
                </tr>`;

                


            });

        }).catch((result) => {
            console.error('Errore nella richiesta', result);
            alert('Errore durante la richiesta');
        })
}

function deletePizza(pizzaId) {
    const risposta = confirm('Sei sicuro?');

    if (risposta) {
        axios.delete(`http://localhost:8080/api/${pizzaId}`)
            .then((result) => {
                //ok => ricarico l'elenco dei libri
                pizzasList();
            }).catch((result) => {
                console.error('Errore nella richiesta', result);
                alert('Errore durante la richiesta!');
            });
    }

}