const express = require('express')
const tasksApp = express()
const fs = require('fs');
const tasks = JSON.parse(fs.readFileSync('./tasks.json', 'utf8'));

tasksApp.get('/', (req, res) => {
    res.send('Hello Friend')
})

tasksApp.get('/tasks', (req, res) => {
    res.send(tasks)
})

//setting up a new task and adding it
tasksApp.get("/tasks/new", (req, res) => {
    
	//taking the params from the URL
    var addedTask = {
			"id": req.query.id,
			"task": req.query.task,
	};
	
    //adding the new task to the list
	tasks.tasksInfo.push(addedTask)
	
    fs.writeFile('./tasks.json', JSON.stringify(tasks), (err) => {
        if (err) {
			res.send(err);
		} else {
			res.send(`Added the task successfully`);
		}
    })

})


//removing existing tasks
tasksApp.get("/tasks/remove", (req, res) => {
    //understanding which task it is according to params
    let taskToDelete = req.query.id
	//console.log(`finished to define task to delete`)
	//deleteing the task from the list
    tasks.tasksInfo = tasks.tasksInfo.filter(task => task.id != taskToDelete)
	//console.log(`finished to filter`)
	
    fs.writeFile('./tasks.json', JSON.stringify(tasks), (err) => {
        if (err) {
			res.send(err);
			
		} else {
			res.send(` ${taskToDelete} was deleted successfully`);
		}
    })

})

tasksApp.listen(8080, () => {
    console.log(`server is running on port 8080`)
})
