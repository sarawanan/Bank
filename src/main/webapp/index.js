import {api, $} from './common.js';

$('login').addEventListener('click', () => {
    console.log("Submit login information");
    document.location.href='/login/' + $('name').value;
});