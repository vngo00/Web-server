import React from 'react';
import Cookies from 'universal-cookie';


function RPS(props){

    const [rps, setRPS] = React.useState('');
    const [cpu, setCpu] = React.useState('');
    const [result, setResult] = React.useState('');


    function cpuRandom(){
        let cpuRPS = ['Rock', 'Paper', 'Scissor'],
        cpuChoice = cpuRPS [Math.floor(Math.random()*cpuRPS.length)];
        return cpuChoice;
    }

    function play(){
        const cookies = new Cookies();
        const rpsDto = {
            rps: rps,
            cpu: cpuRandom(),
        };

        fetch('/getRPS', {
            method : 'POST',
            body:JSON.stringify(rpsDto),
            headers: {
                auth : cookies.get('auth'), 
            }
        })
        .then(response => response.json())
        .then((apiRes) => {
            console.log(apiRes);
            if(apiRes.status){
                setResult(apiRes.message);
            }
        })
        .catch((err) => {
            console.log(err);
        });
    }

    return (
        <div>
          <h1> Rock Paper Scissor</h1>
          <h2>Welcome {props.loggedInUser}</h2>
          <div>
            <input name="g1" type="radio" value={rps} onChange={(e) => setRPS("Rock")}/>Rock
            <input name="g1" type="radio" value={rps} onChange={(e) => setRPS("Paper")}/>Paper
            <input name="g1" type="radio" value={rps} onChange={(e) => setRPS("Scissor")}/>Scissor

            <div></div>
            <button onClick={play}>Play</button>
        </div>
            {result}
        </div>
      );
}



export default RPS;