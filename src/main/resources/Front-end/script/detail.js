

const URLParams = new URLSearchParams(window.location.search);
const pizzaId = URLParams.get('id');

pizzaDetail();

function pizzaDetail() {
    axios.get(`http://localhost:8080/api/${pizzaId}`)
        .then((result) => {

            const pizza = result.data;
            console.log('Richiesta ok', pizza);

            document.querySelector('#nome').innerHTML = `${pizza.nome}`;
            document.querySelector('#pizza-img').src = `${pizza.foto}`;
            document.querySelector('#descrizione').innerHTML = `${pizza.descrizione}`;
            document.querySelector('#prezzo').innerHTML = `${pizza.prezzo}€`;

            

            

            pizza.ingredienti.forEach(ingrediente => {
                document.querySelector('#ingredienti').innerHTML += `
               ${ingrediente.nome}
                `;
            });


           

        }).catch((result) => {
            console.error('Errore nella richiesta', result);
            alert('Errore durante la richiesta');
        })
}

