const express = require('express');
const request = require('request');
const PORT = 8080;
let app = express();
let apiKey = 'SVDPKKA6B4XY8PM1';
const DEFAULT_STOCK = 'FB';


app.get('/stock', (req, res, next) => {


	
	let stockSymbol = req.query.symbol || DEFAULT_STOCK;
	console.log(stockSymbol);

	fetchPriceForStock(stockSymbol, (err, price) =>{
 		if (err) return res.status(500).json({err: err.message});
 		return res.json(price);
 	});
	
    
});


function fetchPriceForStock(stock, cb){

	let url = `https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=${stock}&apikey=${apiKey}`

	request.get({
    url: url,
    json: true,
    headers: {'User-Agent': 'request'}
  	}, (err, res, data) => {
	    if (err) {
	      console.log('Error:', err);
	    } else if (res.statusCode !== 200) {
	      console.log('Status:', res.statusCode);
	    } else {
	      console.log(data);
	      Price = data['Global Quote']['05. price']
	      console.log(`The Price is ${Price}`);

	      return cb(null, {
	      	symbol: stock,
	      	price: Price
	      });

	    }
	});
}


app.listen(PORT, () => {
    console.log(`Listening on port ${PORT}`);
});

