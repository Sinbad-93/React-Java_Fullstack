import axios from "axios";
import React, { useEffect,useState } from "react";
import { Link, useParams } from "react-router-dom";
import style from "./userOrders.module.css"

export default function UserOrders(props) {
  const [orders, setOrder] = useState([]);

  const { id } = useParams();

  useEffect(() => {
    loadOrder();
  }, []);

  const loadOrder = async () => {
    const result = await axios.get(`http://localhost:8080/orders/${props.userid}`);
    setOrder(result.data);
  };

  return (

   <>
        {orders.length > 0 && <div className={style.flex}>
          <span >Orders :</span>
          {orders.map((order, index) => (
          <div className={style.flex2} >

                <span className={style.wrapper}>
                  <span className={style.indication}>#Number : </span>
                  <span> {order.id} </span></span>

                    <span className={style.wrapper}>
                  <span className={style.indication}>Name: </span>
                  <span> {props.userName} </span></span>
                  <span className={style.wrapper}>
                  <span className={style.indication}>Product: </span>
                  <span> {order.product} </span></span>
                  <span className={style.wrapper}>
                  <span className={style.indication}>Price: </span>
                  <span>{order.price} </span></span>
              
     
          </div>))}
        
        </div>}</>

  );
}
