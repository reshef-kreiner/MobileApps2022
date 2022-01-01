const express = require('express');
const request = require('request');
const PORT = 8080;
let app = express();

app.get('/:stockName', (req, res, next) => {


    let apiKey = 'SVDPKKA6B4XY8PM1';
    let stockName = req.params.stockName;
	let url = `https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=${stockName}&apikey=${apiKey}`

    request(url, function (err, response, body) {
        if(err){
            console.log('error:', error);
            return res.error(err);
          } else {
            let stockData = JSON.parse(body)
			
			//debugging purpose
			//const stockPrice = parseFloat(body['Global Quote']['05. price']);
		    //let message = `The price of ${stockName} is ${stockPrice}`
            console.log(stockData);
			
            return res.json(stockData);
          }
    });
    
});

app.listen(PORT, () => {
    console.log(`Listening on port ${PORT}`);
});

