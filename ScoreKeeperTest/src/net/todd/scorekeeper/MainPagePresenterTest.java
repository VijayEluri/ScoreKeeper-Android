package net.todd.scorekeeper;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MainPagePresenterTest {
	@Mock
	private MainPageView view;
	@Mock
	private MainPageModel model;
	
	private Listener quitButtonListener;
	private Listener managePlayerButtonListener;
	private Listener startButtonListener;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		MainPagePresenter.create(view, model);
		
		ArgumentCaptor<Listener> listenerCaptor = ArgumentCaptor.forClass(Listener.class);
		verify(view).addQuitButtonListener(listenerCaptor.capture());
		quitButtonListener = listenerCaptor.getValue();
		
		ArgumentCaptor<Listener> managePlayerButtonListenerCaptor = ArgumentCaptor.forClass(Listener.class);
		verify(view).addManagePlayersButtonListener(managePlayerButtonListenerCaptor.capture());
		managePlayerButtonListener = managePlayerButtonListenerCaptor.getValue();
		
		ArgumentCaptor<Listener> startButtonListenerCaptor = ArgumentCaptor.forClass(Listener.class);
		verify(view).addStartButtonListener(startButtonListenerCaptor.capture());
		startButtonListener = startButtonListenerCaptor.getValue();
		
		reset(view, model);
	}
	
	@Test
	public void quitTheApplicationWhenTheQuitButtonIsPressed() {
		quitButtonListener.handle();
		
		verify(model).quitApplication();
	}
	
	@Test
	public void goToTheManagePlayerPageWhenTheManagePlayersButtonIsPressed() {
		managePlayerButtonListener.handle();
		
		verify(model).goToManagePlayerPage();
	}
	
	@Test
	public void goToTheStartGamePageWhenTheStartGameButtonIsPressed() {
		startButtonListener.handle();
		
		verify(model).goToStartGamePage();
	}
}