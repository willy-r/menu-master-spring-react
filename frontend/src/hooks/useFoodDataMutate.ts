import { useMutation, useQueryClient } from "@tanstack/react-query";

import { createFood } from "../services/foods.api";

export const useFoodDataMutate = () => {
  const queryClient = useQueryClient();

  const mutate = useMutation({
    mutationFn: createFood,
    retry: 2,
    onSuccess: () => {
      queryClient.invalidateQueries(["food-data"]);
    },
  });

  return mutate;
};
