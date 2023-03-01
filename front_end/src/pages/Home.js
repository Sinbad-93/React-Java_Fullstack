import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import UserOrders from "../users/UserOrders"

export default function Home() {
  console.log('rendering')
  const [users, setUsers] = useState([]);

  const [showOrder, setShowOrder] = useState(false);

  const [refresh, setRefresh] = useState(false);

  const { id } = useParams();

  useEffect(() => {
    loadUsers();
  }, []);

  const loadUsers = async () => {
    const result = await axios.get("http://localhost:8080/users");
    setUsers(result.data);
  };

  async function deleteUser(id){
    await axios.delete(`http://localhost:8080/user/${id}`);
    loadUsers();
  }

  async function createOrder(id){
    try {
      //génération aléatoire
      var productArray = ['Car', 'Motorbike', 'Bike', 'Boat', 'Plane','Skate'];
      var rand = Math.floor(Math.random()*productArray.length);
      const product = productArray[rand];
      var date = new Date();
      var month = date.getMonth()+1;
      if(month.toString().length <2){
        month = "0"+month.toString();
      }
      var day = date.getDay();
      if(day.toString().length <2){
        day = "0"+day.toString();
      }
      date = date.getFullYear() + "-" + month + "-" + day;
      console.log(date)

      const order = {
        "user" : {"id" : id},
        "price" : Math.floor(Math.random()*1000),
        "product" : product,
        "date" : date
      }
       //await axios.post("http://localhost:8080/user", user);
       //fonctionne tout aussi bien avec fetch 
       
      const response = await fetch('http://localhost:8080/order', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(order)
  });
  const data = await response.json();
  console.log(data);
  setRefresh(!refresh);
  
    } catch (error) {
      console.error(error);
    }
  }

  /* PAREIL AVEC CONST
  const deleteUser = async (id) => {
    await axios.delete(`http://localhost:8080/user/${id}`);
    loadUsers();
  };*/

  return (
    <div className="container">
      <div className="py-4">
        <div className="grid">
         
          
              <span > S.N</span>
              <span >Name</span>
              <span>Username</span>
              <span >Email</span>
              <span >Action  {showOrder && <button onClick={() => setShowOrder(false)}>Hide orders &#8593;</button>}
                {!showOrder && <button onClick={() => setShowOrder(true)}>Show orders &#8595;</button>}</span>
            </div>

          <div>
            {users.map((user, index) => (
              <>
            <div className="grid box">
            
                <span key={index}>
               
                  {index + 1}
                </span>
                <span>{user.name}</span>
                <span>{user.username}</span>
                <span>{user.email}</span>
                <span>
                  <Link
                    className="btn btn-primary mx-2"
                    to={`/viewuser/${user.id}`}
                  >
                    View
                  </Link>
                  <Link
                    className="btn btn-outline-primary mx-2"
                    to={`/edituser/${user.id}`}
                  >
                    Edit
                  </Link>
                  <button
                    className="btn btn-danger mx-2"
                    onClick={() => deleteUser(user.id)}
                  >
                    Delete
                  </button>

                  <button
                  onClick={() =>createOrder(user.id)}
                    className="btn btn-success mx-2"
                  >
                    Add Order
                  </button>
                </span>
                
               
                
              </div>
             {showOrder && <UserOrders refresh={refresh} userid={user.id} userName={user.name}></UserOrders>}
              </>
           
              
            ))}
          </div>
      </div>
    </div>
  );
}
