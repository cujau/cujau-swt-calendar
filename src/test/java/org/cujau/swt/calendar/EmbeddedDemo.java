package org.cujau.swt.calendar;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.cujau.swt.calendar.SWTCalendar;
import org.cujau.swt.calendar.SWTCalendarEvent;
import org.cujau.swt.calendar.SWTCalendarListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.junit.Ignore;

@Ignore
public class EmbeddedDemo {
    public static void main( String[] args ) {
        Display display = new Display();
        Shell shell = new Shell( display );
        shell.setLayout( new GridLayout() );
        final Label l = new Label( shell, SWT.NONE );
        GridData data = new GridData();
        data.grabExcessHorizontalSpace = true;
        data.widthHint = 200;
        l.setLayoutData( data );

        Composite localePanel = new Composite( shell, SWT.NONE );
        localePanel.setLayout( new GridLayout() );
        Label localeLabel = new Label( localePanel, SWT.NONE );
        localeLabel.setText( "Locale:" );
        final Combo localeCombo = new Combo( localePanel, SWT.DROP_DOWN | SWT.READ_ONLY );

        Locale[] temp = Calendar.getAvailableLocales();
        int count = 0;
        for ( int i = 0; i < temp.length; i++ ) {
            if ( temp[i].getCountry().length() > 0 ) {
                count++;
            }
        }

        final Locale[] locales = new Locale[count];

        count = 0;
        for ( int i = 0; i < temp.length; i++ ) {
            if ( temp[i].getCountry().length() > 0 ) {
                locales[count] = temp[i];
                localeCombo.add( locales[count].getDisplayName() );
                count++;
            }
        }

        for ( int i = 0; i < locales.length; i++ ) {
            if ( locales[i].equals( Locale.getDefault() ) ) {
                localeCombo.select( i );
                break;
            }
        }

        final SWTCalendar c = new SWTCalendar( shell, SWT.NONE | SWTCalendar.RED_SUNDAY );
        c.setSelection( new Date() );
        data = new GridData();
        data.grabExcessHorizontalSpace = true;
        c.setLayoutData(data );
        DateFormat df = DateFormat.getDateInstance( DateFormat.LONG, Locale.getDefault() );
        l.setText( df.format( c.getCalendar().getTime() ) );
        c.addSWTCalendarListener( new SWTCalendarListener() {
            public void dateChanged( SWTCalendarEvent calendarEvent ) {
                Locale _locale = locales[localeCombo.getSelectionIndex()];
                DateFormat df2 = DateFormat.getDateInstance( DateFormat.LONG, _locale );
                l.setText( df2.format( calendarEvent.getCalendar().getTime() ) );
            }
        } );

        localeCombo.addSelectionListener( new SelectionListener() {
            public void widgetSelected( SelectionEvent event ) {
                Locale _locale = locales[localeCombo.getSelectionIndex()];
                DateFormat df2 = DateFormat.getDateInstance( DateFormat.LONG, _locale );
                l.setText( df2.format( c.getCalendar().getTime() ) );
                c.setLocale( _locale );
            }

            public void widgetDefaultSelected( SelectionEvent event ) {

            }
        } );
        
        shell.pack();
        shell.open();
        while ( !shell.isDisposed() ) {
            if ( !display.readAndDispatch() )
                display.sleep();
        }
        display.dispose();

    }

}
