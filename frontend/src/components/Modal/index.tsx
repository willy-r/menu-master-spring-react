import { useState } from "react";

import Input from "../Input";
import { useFoodDataMutate } from "../../hooks/useFoodDataMutate";
import { FoodDataCreate } from "../../interfaces/food";

const Modal = () => {
  const [title, setTitle] = useState("");
  const [price, setPrice] = useState(0);
  const [image, setImage] = useState("");

  const { mutate } = useFoodDataMutate();

  const submit = () => {
    const foodData: FoodDataCreate = {
      title,
      price,
      image,
    };
    mutate(foodData);
  };

  return (
    <div className="modal-overflow">
      <div className="modal-body">
        <h2>Register a new food on our menu</h2>
        <form className="input-container">
          <Input label="title" value={title} updateValue={setTitle} />
          <Input label="price" value={price} updateValue={setPrice} />
          <Input label="image" value={image} updateValue={setImage} />
        </form>
        <button onClick={submit} className="btn-secondary">
          Register
        </button>
      </div>
    </div>
  );
};

export default Modal;
