import React from 'react';
import Cookies from 'universal-cookie';


function Friends(props){

    const [conversationIds, setConversationIds] = React.useState([]);
    const [name, setName] = React.useState('');


    function getConversationIds(){
        const cookies = new Cookies();
        fetch('\getUserList', {
            method : 'GET',
            headers: {
                auth : cookies.get('auth'), 
            }
        })
        .then(res => res.json())
        .then(apiRes => {
            console.log(apiRes);
            if(apiRes.status){
                setConversationIds(apiRes.data);
            }
        })
        .catch(e => {
            console.log(e);
        })
    }


    function deleteConversation(){
      const cookies = new Cookies();
      fetch('/deleteConversation?name=' + name,{
        method: 'POST',
        headers: {
          auth : cookies.get('auth'),
        }
      })
      .then(res => res.json())
      .then(apiRes =>{
        console.log(apiRes);
        if(apiRes.status){
          setName('');
        }
      })
    }

  


    return (
        <div>
          <h1> Friends Page</h1>
          <h2>Welcome {props.loggedInUser}</h2>
          <div>
            <button onClick={getConversationIds}>List</button>
        </div>
        
          <div>
          {conversationIds.map(convo => (
            <div>
              {convo.friendName}
            </div>
          ))}

          <div>
          <input value={name} onChange={(e) => setName(e.target.value)}/><button onClick={deleteConversation}>delete</button>
          </div>
        </div>
        </div>
      );
}



export default Friends;