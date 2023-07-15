import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Controller<T> {
    private final T objInstance;

    public Controller(T objInstance) {
        this.objInstance = objInstance;
    }

    public T getObjInstance() {
        return objInstance;
    }

    public Optional<?> actionHandler(Action action) throws ControllerExceptions {
        Method method = getMethod(action.name).orElseThrow(()->
                new ControllerExceptions.ActionRouteException("This is not a valid operation!!!")
        );
        return invoke(method,this,action.values);
    }
    private Optional<Method> getMethod(String actionName){
        Method returnMethod = null;
        List<Method> methods = Arrays.stream(this.getClass().getDeclaredMethods()).collect(Collectors.toList());
        for(Method method : methods){
            if(method.isAnnotationPresent(ActionRouter.class)){
                ActionRouter router = method.getDeclaredAnnotation(ActionRouter.class);
                if(router.actionName().equals(actionName)){
                    returnMethod = method;
                }
            }
        }
        return Optional.ofNullable(returnMethod);
    }
    private Optional<?> invoke(Method method, Object obj, ArrayList<String> values) throws ControllerExceptions {
        try {
            Class<?>[] classes = method.getParameterTypes();
            Object[] objs = new Object[values.size()];
            for (int i = 0; i<objs.length; i++){
                objs[i] = Utilities.changeDataType(values.get(i),classes[i]);
            }
            return Optional.ofNullable(method.invoke(obj,objs));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ControllerExceptions.ActionInvokeException(e.getCause());
        }
    }

}
