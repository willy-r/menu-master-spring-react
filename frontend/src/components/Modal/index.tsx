import { useEffect, useState } from "react";

import Input from "../Input";
import { useFoodDataMutate } from "../../hooks/useFoodDataMutate";
import { FoodDataCreate } from "../../interfaces/food";

import "./styles.css";

interface ModalProps {
  closeModal(): void;
}

const Modal = ({ closeModal }: ModalProps) => {
  const [title, setTitle] = useState("");
  const [price, setPrice] = useState(0);
  const [image, setImage] = useState("");

  const { mutate, isSuccess, isLoading } = useFoodDataMutate();

  useEffect(() => {
    if (isSuccess) {
      closeModal();
    }
  }, [isSuccess]);

  const submit = () => {
    const foodData: FoodDataCreate = {
      title,
      price,
      image,
    };
    mutate(foodData);
  };

  return (
    <div className="modal-overlay">
      <div className="modal-body">
        <h2>Register a new food on our menu</h2>
        <form className="form-container">
          <Input label="Title" value={title} updateValue={setTitle} />
          <Input label="Price" value={price} updateValue={setPrice} />
          <Input label="Image" value={image} updateValue={setImage} />
        </form>
        <button onClick={submit} className="btn-secondary">
          {isLoading ? "Registering..." : "Register"}
        </button>
      </div>
    </div>
  );
};

export default Modal;
