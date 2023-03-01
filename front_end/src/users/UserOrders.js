import axios from "axios";
import React, { useEffect,useState } from "react";
import { Link, useParams } from "react-router-dom";
import style from "./userOrders.module.css"

export default function UserOrders(props) {

/*useEffect( () => {
  loadOrder();
}, [props.refresh]);*/

  const [orders, setOrder] = useState([]);
  const [startPage, setStartPage] = useState(0);
  const [endPage, setSizePage] = useState(2);


  useEffect(() => {
    //Runs only on the first render
    loadOrder();
  }, []);

  useEffect(() => {
    loadOrder();
  }, [startPage]);


  const loadOrder = async () => {
   await axios.get(`http://localhost:8080/orders_pageable/${props.userid}/${startPage}/${endPage}`)
   .then(function (response){

    if(orders.length < 1 ){
      console.log('loadOrder');
      setOrder(response.data);
      return;
    }
    for(let i in response.data){
      orders.push(response.data[i]);
    }


    //It's because the instance of the items array is not changing.
    //That copies the array to a new identical array. 
    //React will see that it's different and will re-render
    setOrder([...orders])

    }).catch(function(err) {
      console.log(err);
    })
    
  };

  function newPages(){
    // 2 pages suivantes
    setStartPage(startPage+1);
  }

  return (

   <>
   {startPage} {endPage} {orders.length}
        {orders.length > 0 && <div className={style.flex}>
          <span >Orders :</span>
          {orders.map((order) => 
           (
          <div className={style.flex2} key={order.id}>

                <span className={style.wrapper}>
                  <span className={style.indication}>#Number : </span>
                  <span> {order.id} </span></span>

                    <span className={style.wrapper}>
                  <span className={style.indication}>Name: </span>
                  <span> {props.userName}  {/*{order.userId.email} */} </span></span>
                  <span className={style.wrapper}>
                  <span className={style.indication}>Product: </span>
                  <span> {order.product} </span></span>
                  <span className={style.wrapper}>
                  <span className={style.indication}>Price: </span>
                  <span>{order.price} </span></span>
              
     
          </div>))}
        
        </div>}
        <button onClick={newPages}>Voir + </button> </>

  );
}
