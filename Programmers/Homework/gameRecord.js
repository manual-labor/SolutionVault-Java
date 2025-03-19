const express = require("express");
const bodyParser = require("body-parser");
const fs = require("fs");

const app = express();
app.use(bodyParser.json());

const DATA_DIR = "./data/input";

const records = read_json_file("records.json");

function read_json_file(file_name) {
  const result = JSON.parse(
    fs.readFileSync(`${DATA_DIR}/${file_name}`, "utf8")
  );
  return result;
}

app.get("/api/gamerecord/users", (req, res) => {
  try {

    const result = records.sort((a, b)=>{
      if(a.username == b.username) {
        if(a.tag > b.tag) return 1;
        if(a.tag < b.tag) return -1;
        return 0;
      }else {
        if(a.username > b.username) return 1;
        if(a.username < b.username) return -1;
        return 0;
      }
    }).map((users) => ({ 
        tag: users.tag,
        username: users.username,
      }));
    return res.status(200).send(result);
  } catch (err) {
    return res.status(500).send({error:"Internal Server Error"});
  }
});

app.get("/api/gamerecord/winrate", (req, res) => {
  try {
    const query = req.query;

    if(!query || !query.username || !query.tag) {
      return res.status(401).send({error:"Invalid data format"});
    }

    const findedUser = records.find((userInfo)=>(
     userInfo.username === query.username && userInfo.tag === query.tag
    ));

    if(!findedUser) {
      return res.status(404).send({error:"data not found"});
    }

    result = {winrate : Math.floor(findedUser.win/(findedUser.win + findedUser.lose)*100)}
    
    return res.status(200).send(result);
  } catch (err) {
    return res.status(500).send({error:"Internal Server Error"});
  }
});

const PORT = 5678;
app.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});
