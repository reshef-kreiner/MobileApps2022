const express = require('express')
const pipeApp = express()
const fs = require('fs')
const path = require('path')



//**setting the port to 8080**

pipeApp.listen(8080, function () {
  console.log('port 8080 is now connected')
});

//**opening message

pipeApp.get('/', function (req, res) {
	
  res.send(`This program saves your data into a file, feel free to use it. `);
  
});

//**displaying a specific file** 

pipeApp.get('/files/:file', (req,res) => {


	let filename = req.params.file
	
	const filePath = path.join(__dirname, `/files/${filename}.txt`)
	
	var stat = fs.statSync(filePath)
	
	let readStream = fs.createReadStream(filePath)
	
	readStream.pipe(res)
	
});


//**Specific file was not indicated**

pipeApp.get('/files', function (req, res) {
  res.send(`Enter a valid file name in order to read from it`);
});

