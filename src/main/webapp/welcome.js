import {api, $} from './common.js';

$('topUp').addEventListener('click', () => {
    console.log("TopUp Account balance");
    const request = {
        id : sessionStorage.getItem('id'),
        accountId: $('accountId').value,
        name: $('name').value,
        amount: $('amount').value
    }
    api('http://localhost:8080/account/top-up', 'POST', request)
        .then(response => {
            console.log(response);
            $('balance').innerText = response.balance;
        });
});

$('transfer').addEventListener('click', () => {
    console.log("Transfer amount to another Account");
    const request = {
        id : sessionStorage.getItem('id'),
        accountId: $('accountId').value,
        toAccount: $('toAccount').value,
        name: $('name').value,
        amount: $('transferAmount').value
    }
    api('http://localhost:8080/account/transfer', 'POST', request)
        .then(response => {
            console.log(response);
            $('balance').innerText = response.balance;
            $('liability').innerText = response.liability;
        });
});